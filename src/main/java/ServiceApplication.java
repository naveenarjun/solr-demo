/**
 *
 * Main class for CARE KCL Application
 */

import com.test.solr.model.Book;
import com.test.solr.model.FieldModifier;
import com.test.solr.service.ISearchIndexer;
import com.test.solr.service.SolrIndexer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;


@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@ComponentScan({ "com.test.solr.config","com.test.solr.config","com.test.model","com.test.solr" })

public class ServiceApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(ServiceApplication.class.getName());

    public ServiceApplication() {

    }

    /**
     * Main method
     *
     * @param args String array of inut arguments
     */

    public static void main(final String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
            HashMap<String, FieldModifier> fieldsMap = new HashMap<>();

            FieldModifier fm = new FieldModifier("add", "Lord of the rings 2");
            fieldsMap.put("name", fm);

            Book book  = new Book("cdf5ad9e-561b-45b0-b086-77a0b033dd87", fieldsMap);
            /*book.setId(UUID.randomUUID().toString());
            book.setCat("suspense");
            book.setName("Lord of the Rings 2");*/

            GenericMessage message = new GenericMessage(book);
            SolrIndexer solrIndexer = context.getBean(SolrIndexer.class);
            //solrIndexer.add(message);
            solrIndexer.updatePartial(book);

            /*Collection<GenericMessage> messages  = new ArrayList<GenericMessage>();
            messages.add(message);
            solrIndexer.add(messages);*/


    }


}
