package sk.dejavu.blog.examples.paramconverters.model;

/**
 * @author Michal Gajdos
 */
public class Entity {

    private String foo;
    private String bar;

    public Entity() {
    }

    public Entity(final String foo, final String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(final String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(final String bar) {
        this.bar = bar;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Entity entity = (Entity) o;

        if (bar != null ? !bar.equals(entity.bar) : entity.bar != null) {
            return false;
        }
        if (foo != null ? !foo.equals(entity.foo) : entity.foo != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = foo != null ? foo.hashCode() : 0;
        result = 31 * result + (bar != null ? bar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "foo='" + foo + '\'' +
                ", bar='" + bar + '\'' +
                '}';
    }
}
