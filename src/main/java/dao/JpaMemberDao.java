/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Facility;
import entity.Member1;
import java.util.List;

/**
 *
 * @author ahas36
 */
public class JpaMemberDao extends JpaDao<Member1, Integer> implements MemberDao{
    
    public JpaMemberDao() {
        super(Member1.class);
    }

    
}
