package me.wcc.iems.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @Override
    public String toString() {
        return "TestTable{" + "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
