package com.sau.mm.model;

import com.sau.mm.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="category_seq")
    @SequenceGenerator(name="category_seq", sequenceName="category_seq", initialValue = 1 , allocationSize=1)
    private long id;

    @Column(length = 16)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Classification> classifications;

    public Category(CategoryDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.name = categoryDTO.getName();
    }

    public CategoryDTO viewAsCategoryDTO() {
        return new CategoryDTO(id, name);
    }
}