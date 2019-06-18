package examples.Library;

import annotations.CustomField;
import annotations.Numerical;
import annotations.Stringified;
import annotations.TypescriptClass;

@TypescriptClass(withConstructor = false)
public class Author {

    @Numerical
    private long id;

    @Stringified
    private String name;

    @CustomField(name = "any")
    private String bio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
