package com.train.mp.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * <p>
 * 论坛模块--帖子评论表 前端控制器
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {


    public static void main(String[] args) {
        LinkedHashSet<Integer> list = new LinkedHashSet<>();
        list.add(1);
    }

}
