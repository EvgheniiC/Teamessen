package eventim.teamessen.form;

public class UserTeamForm {

    private String userName;

    private String teamName;


    public UserTeamForm(String userName, String teamName) {
        this.userName = userName;
        this.teamName = teamName;
    }

    public UserTeamForm() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }






}
