package com.example.springkafka.event;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Greeting {

    private String msg;
    private String name;

}
