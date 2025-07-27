package com.example.java_transaction_generator;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaTransactionGeneratorApplication {

	public static void main(String[] args) {

		String topic = "transactions";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		ObjectMapper mapper = new ObjectMapper();

		while(true) {
			Transaction txn = TransactionGenerator.generate();
			String json = mapper.writeValueAsAString(txn);

			ProducerRecord<String, String> record = new ProducerRecord<>(topic, txn.userId, json);
			producer.send(record);
			System.out.println("Sent: " + json);

			Thread.sleep(1000);
		}
	}

}
