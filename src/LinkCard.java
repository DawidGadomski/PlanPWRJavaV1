import java.net.URI;

public class LinkCard {
    private String linkName;
    private URI linkURI;

    public LinkCard(String linkName, URI linkURI) {
        this.linkName = linkName;
        this.linkURI = linkURI;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public URI getLinkURI() {
        return linkURI;
    }

    public void setLinkURI(URI linkURI) {
        this.linkURI = linkURI;
    }
}
