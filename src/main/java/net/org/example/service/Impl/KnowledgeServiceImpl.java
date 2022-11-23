package net.org.example.service.Impl;


import net.org.example.dao.KnowledgeDao;
import net.org.example.domain.Knowledge;
import net.org.example.domain.Question;
import net.org.example.service.KnowledgeService;

import java.util.List;

/**
 * 知识点服务层
 */
public class KnowledgeServiceImpl implements KnowledgeService {

    private KnowledgeDao knowledgeDao = new KnowledgeDao();

    /**
     * 添加知识点 -> 内容不能重复
     * @param content
     * @param createPerson
     * @return
     */
    @Override
    public int addKnowledge(String content,String createPerson) {
        Knowledge knowledge = findKnowledgeByContent(content);
        if (knowledge == null){ //如果知识点不存在, 则可以添加 知识点不能重复
            return knowledgeDao.addKnowledge(content,createPerson);
        }else {
            return 0;
        }
    }

    /**
     * 查询知识点内容,避免重复
     * @param content
     * @return
     */
    @Override
    public Knowledge findKnowledgeByContent(String content) {
        return knowledgeDao.findKnowledgeByContent(content);
    }

    /**
     * 查询所有知识点, 知识点共享
     * @return
     */
    @Override
    public List<Knowledge> listKnowledge() {
        return knowledgeDao.listKnowledge();
    }

    /**
     * 删除知识点 要求： 未绑定试题, 只能删除自己的
     * @param content
     * @param name
     * @return
     */
    @Override
    public void delKnowledge(String content,String name) {

    }

    /**
     * 试题资源模块 -> 添加试题 要求:绑定的知识点必须是已经存在的，不限于自己创建的知识点，可绑定他人创建的
     * @param question
     * @return
     */
    @Override
    public int addQuestion(Question question) {
       return 0;
    }

    /**
     * 当创建试题的时候,需要绑定知识点,则需要修改知识点的绑定状态
     * @param content
     * @param createPerson
     * @return
     */
    @Override
    public int updateBind(String content, String createPerson) {
        Integer questionId = findIdByContent(content);
        return knowledgeDao.updateBind(questionId,content,createPerson);
    }

    /**
     * 创建试题, 修改绑定状态
     * @param content
     * @return
     */
    @Override
    public Integer findIdByContent(String content) {
        return knowledgeDao.findIdByContent(content);
    }

    /**
     * 试题资源列表 -> 要求: 列表只展示自己创建的试题资源
     * @param createPerson
     * @return
     */
    @Override
    public List<Question> listQuestion(String createPerson) {
        return knowledgeDao.listQuestion(createPerson);
    }

    /**
     *试题资源模块 -> 删除试题 ->
     *要求: 只能删除自己创建的试题资源, 删除试题后,对应的知识点解绑定,查看该知识点是否绑定了试题,没有则不删除
     * @param createPerson
     * @param content
     * @return
     */
    @Override
    public void delQuestion(String createPerson, String content) {

    }

    /**
     * 创建试题,绑定知识点
     * @param questionId
     * @param content
     * @return
     */
    public int questionBind(Integer questionId,String content){
        return knowledgeDao.questionBind(questionId,content);
    }

    /**
     * 用来判断正确选项是否是A B C D其中之一
     * @param optionTrue
     * @return
     */
    public boolean isTrue(String optionTrue){
        String[] array =  {"A","B","C","D"};
        for (String s : array) {
            if (s.equalsIgnoreCase(optionTrue)){  //如果有相等的,则之间返回格式正确
                return true;
            }
        }
        return false;
    }
}
