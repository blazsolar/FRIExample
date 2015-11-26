package si.dlabs.friexample.data.api.model;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class JokeResponse {

    private String type;

    private JokeValueResponse value;

    public String getType() {
        return type;
    }

    public JokeValueResponse getValue() {
        return value;
    }
}
