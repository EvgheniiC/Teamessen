package eventim.teamessen.form;

public class TeamForm {

    private String name;

    private String url;

    public TeamForm() {
    }

    public TeamForm(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public TeamForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
