package by.bsuir.lr4;


//import jakarta.jms.Queue;

import jakarta.ejb.embeddable.EJBContainer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class ArrayMessageProducer {
//    @Resource
//    private ConnectionFactory connectionFactory;
//    @Resource
//    private Destination destination;
    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "MessageConsumer")
    private Destination questionQueue;

//    @Resource(name = "AnswerQueue")
//    private Queue answerQueue;


    public void sendMessage(Numbers numbers) {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer messageProducer = session.createProducer(destination);
            MessageProducer questions = session.createProducer(questionQueue);
//            questions.send(session.createTextMessage("privet"));
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(numbers);
            objectMessage.acknowledge();
            questions.send(objectMessage);
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
