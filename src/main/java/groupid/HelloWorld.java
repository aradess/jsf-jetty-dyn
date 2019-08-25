package groupid;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class HelloWorld {

    private final static List<String> ENTITIES = Arrays.asList(new String[]{"1", "2", "3", "4", "5"});

    private final String[] array = new String[]{"1", "2", "3", "4", "5"};
    private List<TestList> listTextArea;

    @PostConstruct
    public void init() {
        selectedFields = ENTITIES.stream().filter(s -> !"5".equals(s)).collect(Collectors.toSet());
        availableFields = ENTITIES.stream().filter(s -> "5".equals(s)).collect(Collectors.toSet());
        values.put("1", "1");
        values.put("2", "2");
        values.put("3", "3");
        values.put("4", "4");
        //----------
        listTextArea = new ArrayList<>();

    }

    private String selectedType = "";
    private Set<String> availableFields;
    private Set<String> selectedFields;
    private Map<String, String> values = new HashMap<>();

    public Set<String> getAvailableFields() {
        return availableFields;
    }

    public void setAvailableFields(Set<String> availableFields) {
        this.availableFields = availableFields;
    }

    public Set<String> getSelectedFields() {
        return selectedFields;
    }

    public void setSelectedFields(Set<String> selectedFields) {
        this.selectedFields = selectedFields;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public void change(String type) {
        Optional<String> f = availableFields.stream().filter(s -> s.equals(type)).findFirst();
        selectedFields.add(f.get());
        availableFields.remove(f.get());
        values.put(f.get(), null);
    }

    public void onDelete(String type) {
        Optional<String> f = selectedFields.stream().filter(s -> s.equals(type)).findFirst();
        selectedFields.remove(f.get());
        availableFields.add(f.get());
        values.put(f.get(), null);
    }

    public void change2() {
        TestList ttt = new TestList();
        ttt.setNomerRow("1");
        ttt.setArea(selectedType);
        listTextArea.add(ttt);
    }

    public void onDelete2(TestList test) {
        if (!listTextArea.isEmpty()) {
            listTextArea.remove(test);
            System.out.println(listTextArea.size());
        }
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public List<TestList> getListTextArea() {
        return listTextArea;
    }

    public void setListTextArea(List<TestList> listTextArea) {
        this.listTextArea = listTextArea;
    }
}

//<!--                        <p:commandButton value="x" action="#{helloWorld.onDelete(field)}"-->
//<!--                                         update="main panelRepeatWrapper type">-->
//<!--                            <p:ajax event="click"-->
//<!--                                    update="main panelRepeatWrapper"/>-->
//<!--                        </p:commandButton>-->