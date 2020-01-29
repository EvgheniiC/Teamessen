package eventim.teamessen.form;

import java.util.Date;

public class EventForm {

    private String name;

    private Date beginDate;

    public EventForm(String name, Date beginDate) {
        this.name = name;
        this.beginDate = beginDate;
    }

    public EventForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
}
