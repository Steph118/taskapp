package com.steph18.demo.contoller;

import com.steph18.demo.entities.CategoryTask;
import com.steph18.demo.entities.User;
import com.steph18.demo.service.CategoryTaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryTaskController {

    private final CategoryTaskService categoryTaskService;

    @Autowired
    public CategoryTaskController(CategoryTaskService categoryTaskService) {
        this.categoryTaskService = categoryTaskService;
    }

    @GetMapping("/task-app/pages/category-tasks/list")
    public String showPageCategoryTaskList(Model model) {
        model.addAttribute("users", categoryTaskService.findAll());
        return "pages/task-app/category-tasks/list";
    }


    /*@PostMapping({"/task-app/pages/category-tasks/edit/"})
    public String updateUser(@PathVariable(required = false) Long id, @Valid CategoryTask categoryTask,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryTask);
            return "pages/task-app/category-tasks/edit";
        }
        this.categoryTaskService.update(categoryTask);
        return "redirect:/users"; // Redirige vers la liste des utilisateurs si tout va bien
    }*/

    @GetMapping({"/task-app/pages/category-tasks/edit", "/{id}"})
    public String userForm(@PathVariable(required = false) Long id, Model model) {
        CategoryTask catNew = (id != null) ? categoryTaskService.findById(id) : new CategoryTask();
        model.addAttribute("category", catNew);
        return "pages/task-app/category-tasks/edit";
    }

    // Enregistrer ou mettre à jour un utilisateur
    @PostMapping({"/task-app/pages/category-tasks/edit", "/{id}"})
    public String saveUser(@PathVariable(required = false) Long id, @ModelAttribute CategoryTask category) {
        if (id != null) {
            //user.setId(id);
        }
        //userService.saveUser(user);
        return "redirect:/users";
    }

}
