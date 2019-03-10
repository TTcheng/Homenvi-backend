package me.wcc.iems.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author chuncheng.wang@hand-china.com 19-3-6 下午8:44
 */
@Table(name = "test")
public class TestTable {
    @GeneratedValue
    @Id
    private Integer id;
    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestTable(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof TestTable) {
            TestTable testTable = (TestTable) o;
            return getId().equals(testTable.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "TestTable{" + "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
