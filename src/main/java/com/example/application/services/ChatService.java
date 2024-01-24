package com.example.application.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import org.atmosphere.config.service.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@BrowserCallable
@AnonymousAllowed
@Service
public class ChatService {
    interface Assistant {
        String chat(String message);
    }

    @Value("${app.openaikey}")
    private String OPEN_AI_KEY;
    private Assistant assistant;

    @PostConstruct()
    void init() {
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
         assistant = AiServices.builder(Assistant.class).chatLanguageModel(OpenAiChatModel.withApiKey(OPEN_AI_KEY)).chatMemory(chatMemory).build();
    }


    public String chat(String message) {
        return assistant.chat(message);
    }
}
