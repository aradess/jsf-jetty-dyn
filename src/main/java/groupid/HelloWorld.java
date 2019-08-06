package groupid;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class HelloWorld {
    private final static List<String> ENTITIES = Arrays.asList(new String[]{"1", "2", "3", "4", "5"});

    @PostConstruct
    public void init() {
        selectedFields = ENTITIES.stream().filter(s -> !"5".equals(s)).collect(Collectors.toSet());
        availableFields = ENTITIES.stream().filter(s -> "5".equals(s)).collect(Collectors.toSet());
        values.put("1", "1");
        values.put("2", "2");
        values.put("3", "3");
        values.put("4", "4");
    }

    private String selectedType = "";
    private Set<String> availableFields;
    private Set<String> selectedFields;
    private Map<String, String> values = new HashMap<String, String>();

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

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
}
