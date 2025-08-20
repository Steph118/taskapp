package com.steph18.demo.contoller;

import com.steph18.demo.entities.CategoryTask;
import com.steph18.demo.service.CategoryTaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CategoryTaskController.class);

    @Autowired
    public CategoryTaskController(CategoryTaskService categoryTaskService) {
        this.categoryTaskService = categoryTaskService;
    }

    @GetMapping("/task-app/pages/category-tasks/list")
    public String showPageCategoryTaskList(Model model) {
        model.addAttribute("categories", categoryTaskService.findAll());
        return "pages/task-app/category-tasks/list";
    }

    @GetMapping("/task-app/pages/category-tasks/edit")
    public String addNewCategoryTaskForm(Model model) {
        model.addAttribute("category", new CategoryTask());
        return "pages/task-app/category-tasks/edit";
    }

    @GetMapping("/task-app/pages/category-tasks/edit/{id}")
    public String updateCategoryTaskForm(@PathVariable Long id, Model model) {
        CategoryTask catToUpdate = (id != null) ? categoryTaskService.findById(id) : new CategoryTask();
        model.addAttribute("category", catToUpdate);
        return "pages/task-app/category-tasks/edit";
    }

    @PostMapping("/save/category-tasks")
    public String saveCategoryTask(@ModelAttribute("category") @Valid CategoryTask categoryTask,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "pages/task-app/category-tasks/edit";
        }
        logger.info("Category task: {}", categoryTask);
        if (categoryTask.getId() == null) {
            this.categoryTaskService.save(categoryTask);
            logger.info("Save category task");
        } else {
            this.categoryTaskService.update(categoryTask);
            logger.info("UUpdate category task");
        }
        return "redirect:/task-app/pages/category-tasks/list";
    }

}
