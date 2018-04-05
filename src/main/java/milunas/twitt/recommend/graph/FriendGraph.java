package milunas.twitt.recommend.graph;

import milunas.twitt.model.User;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.social.facebook.api.GraphApi;

import java.util.Map;

public class FriendGraph {

    private User vertice;
    private Map<User, User> edge;

    public void joinVertice(){
        GraphDatabaseFactory graph = new GraphDatabaseFactory();
        graph.newEmbeddedDatabase();
    }

    public void findCycle(){

    }

}
