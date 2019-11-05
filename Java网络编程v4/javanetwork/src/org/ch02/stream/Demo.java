package org.ch02.stream;

import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.Line;

import org.junit.Test;

public class Demo {

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.generateCharacters3(System.out);
    }

    // loop print printable ASCII characters
    @Test
    public void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharacterPerLine = 72;

        while (true) {
            for (int i = firstPrintableCharacter; i < firstPrintableCharacter + numberOfCharacterPerLine; i++) {
                out.write(((i - 33) % numberOfPrintableCharacter) + 33);
            }
            out.write('\r');
            out.write('\n');
            firstPrintableCharacter = ((firstPrintableCharacter + 1) - 33) % numberOfPrintableCharacter + 33;
        }
    }

    public void generateCharacters2(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharacterPerLine = 72;

        int start = firstPrintableCharacter;

        while (true) {
            for (int i = start; i < start + numberOfCharacterPerLine; i++) {
                out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacter) + firstPrintableCharacter);
            }
            out.write('\r');
            out.write('\n');
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacter + firstPrintableCharacter;
        }
    }

    public void generateCharacters3(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharacterPerLine = 72;
        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharacterPerLine + 2];

        while (true) {
            for (int i = start; i < start + numberOfCharacterPerLine; i++) {
                line[i - start] = (byte) (((i - firstPrintableCharacter) % numberOfPrintableCharacter)
                        + firstPrintableCharacter);
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';
            out.write(line);
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacter + firstPrintableCharacter;
        }
    }

}
