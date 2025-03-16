// src/main/java/buyogo/com/utils/CodeGenerator.java
package buyogo.com.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public String generateCenterCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARS.length());
            sb.append(CHARS.charAt(index));
        }

        return "TC-" + sb.toString();
    }
}
