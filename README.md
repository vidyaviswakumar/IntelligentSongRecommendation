# BigDataProject-DataSense

This is a repository that contains the list of links of the repositories of the project.
Project is based on recommending songs on 3 levels:
Analyze user mood from text and getting Mahout-Recommendation for that user, combining with Trending song and with the predicted mood of the song based on its lyrics.

Begin With:

Pre-requisites:
-MongoDB
-AWS
-STS
-NetBeans/Eclipse

Dataset:
To get the dataset:
Refer: https://labrosa.ee.columbia.edu/millionsong/pages/getting-dataset
Since the actual MSD is huge around 280Gb, to start you can use subset provided.
 
Actual Dataset can be accessed from AWS :
You can use Amazon EC2 virtual machine to run your experiments in the cloud. You simply set up an EBS disk instance from snap-5178cf30.

Next, you need user information to provide recommendations. Get user data from “The Echo Nest Taste Profile Subset” - https://labrosa.ee.columbia.edu/millionsong/tasteprofile
This triplet dataset consists of user_id, song_id and play_count where user_id and song_id are strings.

1.	Helper Function in JAVA to bring the triplet in the desired format

Path:DataSense-BigDataProject\DataSense_HelperFunctions\src\readfile\ReadFile.java

User Id	Track Id	Play Count
fd50c4007b68a3737fe052d5a4f78ce8aa117f3d	SOBONKR12A58A7A7E0	1
9be82340a8b5ef32357fe5af957ccd54736ece95	SOHGGAH12A58A795BE	15
In the format of:
User Id	Track Id	Play Count
1	1	1
10	22	15

For Mahout recommendation format:

This requires a triplet file with user_Id,song_Id and playCount. All these csv’s are available in 
Path: “DataSense-BigDataProject\DataSense_HelperFunctions” (triplets.txt from dataset used).
To properly format the input for Mahout, run ReadFile.java with the csv derived from triplets.txt(input_to_mahout_derived.csv)
 Output csv - “input_to_mahout.csv”, which you can directly use as Mahout input.
 
To run Mahout user-based recommendation use the following command:

bin/hadoop jar mahout-core-0.7-job.jar org.apache.mahout.cf.taste.hadoop.item.RecommenderJob -Dmapred.input.dir=input_reco/input_to_mahout.csv 
-Dmapred.output.dir=output_mahout_reco 
--numRecommendations 30 
--booleanData true 
--similarityClassname SIMILARITY_COOCCURRENCE


Once, you get Mahout Output, run ConvertMahoutOutput.java - which will take mahout output as csv and return a json. We are storing this data in MongoDb, to further perform refining queries on it. Since it is an unstructured data, writing it to a json and then importing to MongoDb became easy.

To import this mahout_output_json into MongoDb, use the below command(run outside of mongoshell):
mongoimport -d dataSense -c mahout_reco_output --type json --file “Your-Path-Goes-Here”\ReadFile\ReadFile\mahout_reco_output.json –jsonArray

2.	Functions to Retrieve data from HDF5 files

Path:  /DataSense-BigDataProject /HDF5_Extract

HDF5 files are Hierarchical Data Files which consists of subsets of many data. There are separate HDF5 files for each track. Each trackId is unique but a song can exist in multiple tracks.

You need to write HDF5_getters.java to retrieve the data to a txt or csv format. You need to install java to run the application in the instance. You’ll need a jar dependency on hdf-java-2.6.1. 

Follow the following commands to run the class in Ubuntu Instance of AWS. Make sure you create a large or xlarge instance and attach SSD volume of more than 600gb data. 

javac -classpath hdf-java-2.6.1.jar hdf5_getters.java
java -cp :hdf-java-2.6.1.jar hdf5_getters

After you run this, you get the entire dataset in text. Copy the same into a csv and run import to MongoDb. Import command(run outside of mongoshell):
mongoimport -d dataSense -c songs_dataset --type csv --file “Your-Path-Goes-Here”\data_for_mongodb\songs_dataset.csv --headerline



3.	Helper function in JAVA to get the mood of the lyrics
You have to run lyricsMood.java to get mood for all the tracks. We fetch this from reading the lyrics. To get lyrics, we are hitting musiXmatch API. This provided us a dataset which had mapping between MSG track_id and musiXmatch trackId.

So you just have to run lyricsMood.java, which takes this file as input and with help of ToneAnalyzer API we get the mood of lyrics. Output of this program, saves a lyricsMood.csv file which we input it to MongoDB.

4.	Machine Learning for Genre classification of the songs
Python code to generate genre classification –
The idea is to use artist tags in the MSD that describe typical genres. From the artists tagged by these, we extract simple features from all their tracks. The Echo Nest terms are a little too complicated and diverse to be used for that purpose. To get a handful of genres, we would have to handpick a large number of tags and merge the related ones into genres classes. For instance, 'us pop', 'pop', 'indie pop', 'American pop', ... could all be merged into 'pop.

You directly run genreclassifier.py on hdf5 files directly, and it will read and return a file containing all tracks with their genre.

5.	Lyrics_info
Merge the output files coming from Part3 and Part 4 using R.
The merged file is directly imported to MongoDB as one csv- lyrics_info.csv.

Import command:
mongoimport -d dataSense -c lyrics_info --type csv --file “Your-Path-Goes-Here”\data_for_mongodb\lyrics_info.csv --headerline

6.	Fetch  Trending songs using MongoDb Query
Trending songs are based on the total play count of songs irrespective of the user_id. The song that is played the most across all the user is the most trending song. 
Also our dataset’s most recent song is from year 2010.
So, we further fetch trending songs on basis of year.
 
MongoDB Queries(run on RoboMongo / MongoShell):
//to fetch trending songs by year
db.triplets_new.aggregate(
[
   { $group: {
       _id : "$msd_song_id",
             "totalPlayCount":{$sum:"$play_count" },
             }
    },
   { $sort : {totalPlayCount:-1}},
  {
       $out:"trending_songs"
   }])   
   ---------------------------------------------------------------------
   //filtering songs on year>2008 from songs_dataset
   db.songsfiltered.insert(db.songs_dataset.aggregate([ { $match: { Year: {$gt : 2008} } }]));
   -----------------------------------------------------------------------
   
   //final trending songs_collection that u hAE TO USE
   db.trending_songs.aggregate(
[
     {$lookup: {from: "songsfiltered", localField: "_id", foreignField: "Song_id", as: "trending_songs_filtered_year"}},   
   {$out:"trending_songs_filtered_year"}
]
   )



7.	Spring App
Finally, our application is deployed in our springApp where we integrate all our modules.
It eill take input from user in the form of text and smileys and predict user mood.

Get Mahout Recommendations for that user from MongoDb and give playlists to user.
•	Playlist 1: Might cheer you up (Mahout Recommendations + Mood + Trending)
•	Playlist 2: Match your preferences (Mahout Recommendations + Trending)
•	Playlist 3: Recommended for you (Mahout Recommendations)

This application is in the \DataSense-BigDataProject_Git_Version\DataSense_SpringApplication\FinalProject.
You will need following maven dependencies to run your project:

Mongo Connector:
        
 https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver
https://mvnrepository.com/artifact/org.springframework.data/spring-data-commons 
 https://mvnrepository.com/artifact/org.springframework.data/spring-data-mongodb https://mvnrepository.com/artifact/org.springframework.data/spring-data-mongodb 

Tone Analyser:
https://mvnrepository.com/artifact/com.ibm.watson.developer_cloud/tone-analyzer

Gson Dependency
https://mvnrepository.com/artifact/com.google.code.gson/gson
	
