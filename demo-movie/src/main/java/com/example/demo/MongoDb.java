package com.example.demo;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDb {
    public static void main(String[] args) {
        String connectionString = 
        "mongodb+srv://vincentchunwai:Aa35675465@cluster0.gqsh6el.mongodb.net"; // Replace with your MongoDB server URL

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("moive-api-db"); // Replace with your database name
            MongoCollection<Document> collection = database.getCollection("movies"); // Replace with your collection name

            // Fetch data from MongoDB
            FindIterable<Document> documents = collection.find();
            for (Document document : documents) {
                System.out.println(document.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

