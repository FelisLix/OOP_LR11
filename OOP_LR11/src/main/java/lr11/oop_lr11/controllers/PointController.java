package lr11.oop_lr11.controllers;

import lombok.AllArgsConstructor;
import lr11.oop_lr11.service.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class PointController {
    private PointService pointService;

    @GetMapping("/points")
    public String showPoints(Model model){
        model.addAttribute("points", pointService.getPoints());
        return "points";
    }

    @PostMapping("/tabulate")
    public String createPoints(
            @RequestParam("start") double x1,
            @RequestParam("finish") double x2,
            @RequestParam("step") double step){
        pointService.createPointsArray(x1, x2, step);
        return "redirect:/points";
    }

    @PostMapping("/show_smallest")
    public String showSmallest(){
        pointService.show_smallest();
        return "redirect:/points";
    }

    @PostMapping("/show_biggest")
    public String showBiggest(){
        pointService.show_biggest();
        return "redirect:/points";
    }
    @PostMapping("/show_ArithmSum")
    public String showArithm(Model model){
        model.addAttribute("points", pointService);
        return "ArithmSum";
    }



}
