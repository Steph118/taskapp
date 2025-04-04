package com.steph18.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String showPageIndex() {
        return "index";
    }

    @GetMapping("/pages/layouts/edit")
    public String showPageLayoutEdit() {
        return "pages/layouts/layout-edit";
    }

    @GetMapping("/task-app/pages/category-tasks/edit")
    public String showPageCategoryTaskEdit() {
        return "pages/task-app/category-tasks/edit";
    }

    @GetMapping("/task-app/pages/category-tasks/list")
    public String showPageCategoryTaskList() {
        return "pages/task-app/category-tasks/list";
    }
}
