package by.bsuir.lr4;


import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

@MessageDriven(mappedName = "MessageConsumer")
public class MessageConsumer implements MessageListener {

//    @Resource
//    MessageDrivenContext mdc;
    @Resource
    private ConnectionFactory connectionFactory;

//    @Resource(name = "AnswerQueue")
//    private Queue answerQueue;

    @Override
    public void onMessage(Message message) {
        Numbers numbers = null;
        try {
            numbers = message.getBody(Numbers.class);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        System.out.println("accepted: " + numbers);

        Integer minValue = numbers.getNumbers().stream().min(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        Integer maxValue = numbers.getNumbers().stream().max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        String sortedNumbers = numbers.getNumbers().stream().sorted().map(String::valueOf).reduce((s1, s2) -> s1 + ", " + s2).orElseThrow(RuntimeException::new);

        try (FileWriter fileWriter = new FileWriter("D:/БГУИР/РИС/LR4/src/main/resources/out.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String line = String.format("Min value : %d, Max value : %d, array: %s", minValue, maxValue, sortedNumbers);
            bufferedWriter.write(line);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }

    }
}
