package demo.service;

import demo.repository.po.Demo;

/**
 * DemoService
 * @author 水张哲
 * @date 2021.07.30
 */
public interface DemoService {
    /**
     * Add one demo
     * @param demo Demo
     * @return This demo's id
     */
    Long add(Demo demo);

    /**
     * Find demo by id
     * @param id Demo’s id
     * @return This demo
     */
    Demo find(Long id);
}
