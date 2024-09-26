package edu.trincoll;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;

public class Programmatic_approach{
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    private final ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                //.modelName("o1-preview")
                .build();

    //Class example
//    @Test
//    void chatWithMessages() {
//        ChatResponse response = chatModel.chat(ChatRequest.builder()
//                .messages(List.of(
//                        new UserMessage("""
//                    Who has the high probaiblity of winning the 2024 US presidential election?
//                    """)))
//                .build());
//        System.out.println(response.aiMessage().text());
//        System.out.println(response.tokenUsage());
//    }

    @Test
    void chatWithString() {
        String answer = chatModel.generate("""
                Who has the high probaiblity of winning the 2024 US presidential election?
                """);
        System.out.println(answer);
    }
    /* Chat output
    As of my last knowledge update in October 2023, I cannot provide real-time predictions or updates on events such as elections. The probability of a candidate winning the 2024 U.S. presidential election can fluctuate based on various factors, including polling data, public sentiment, campaign strategies, economic conditions, and current events.
    As the election approaches, it's important to consult reputable news sources, political analysts, and polling data for the most accurate and up-to-date information regarding the candidates and their chances of winning.
    */

    @Test
    void visionChat() {
        String imageUrl = "https://m.media-amazon.com/images/I/61cvoZ1pXPL._AC_SX679_.jpg";
        Response<AiMessage> response = chatModel.generate(
                UserMessage.from(
                        ImageContent.from(imageUrl),
                        TextContent.from("What do you see? and what you can infer from it? Explain your reasoning step by step.")
                )
        );
        System.out.println(response.content().text());

    }
    /* Chat output:
    The image shows a red baseball cap with the phrase "MAKE AMERICA METAL AGAIN" printed on it, along with stars and some decorative elements in blue and white.

    ### Step-by-Step Reasoning:

    1. **Color and Style**: The cap is predominantly red, a color often associated with political themes in the U.S., particularly in relation to certain political movements.

    2. **Text Analysis**: The phrase on the cap plays on a well-known political slogan. This suggests a connection to American political culture and possibly aims to evoke a sense of patriotism.

    3. **Cultural Reference**: The use of the word "METAL" likely refers to heavy metal music, indicating that this cap is targeting fans of that genre. It combines a political slogan with a cultural reference, suggesting a niche audience.

    4. **Design Elements**: The inclusion of stars and the color scheme (red, white, and blue) reinforces the patriotic theme, common in American political merchandise.

    ### Inference:
    The cap appears to represent a blend of political and musical identity, appealing to individuals who are both patriotic and fans of heavy metal music. It likely aims to promote a sense of community among those who share these interests.
    As of my last knowledge update in October 2023, I cannot provide real-time predictions or updates on events such as elections. The probability of a candidate winning the 2024 U.S. presidential election can fluctuate based on various factors, including polling data, public sentiment, campaign strategies, economic conditions, and current events.

    As the election approaches, it's important to consult reputable news sources, political analysts, and polling data for the most accurate and up-to-date information regarding the candidates and their chances of winning.
    */

}
