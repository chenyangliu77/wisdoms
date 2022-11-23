package net.org.example.service;


import net.org.example.domain.Knowledge;
import net.org.example.domain.Question;

import java.util.List;

/**
 * 知识点表服务层
 */
public interface KnowledgeService {
    /**
     * 添加知识点
     * @param content
     * @param createPerson
     * @return
     */
    int addKnowledge(String content, String createPerson);

    /**
     * 查询知识点, 判断新添加的内容是否已经存在
     * @return
     */
    Knowledge findKnowledgeByContent(String content);

    /**
     * 查询所有知识点, 知识点之间是共享的
     * @return
     */
    List<Knowledge> listKnowledge();

    /**
     * 删除知识点 要求： 未绑定试题, 只能删除自己的
     * @param content
     * @param name
     * @return
     */
    void delKnowledge(String content, String name);

    /**
     * 试题资源模块 -> 添加试题
     * @param question
     * @return
     */
    int addQuestion(Question question);

    /**
     * 当创建试题的时候,需要绑定知识点,则需要修改知识点的绑定状态
     * @param content
     * @param createPerson
     * @return
     */
    int updateBind(String content, String createPerson);

    /**
     * 查询新建的试题id
     */
    Integer findIdByContent(String content);

    /**
     * 试题资源列表 -> 要求: 列表只展示自己创建的试题资源
     * @param createPerson
     * @return
     */
    List<Question> listQuestion(String createPerson);

    /**
     * 试题资源模块 -> 删除试题 ->
     * 要求: 只能删除自己创建的试题资源, 删除试题后,对应的知识点解绑定,查看该知识点是否绑定了试题,没有则不删除
     * @param createPerson
     * @param content
     * @return
     */
    void delQuestion(String createPerson, String content);

}
