package hqnm.promotion;


import hnqm.users.UserDTO;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Hoang
 */
public class PromotionDTO {

    private UserDTO dto;
    private int rank;

    public PromotionDTO() {
    }

    public PromotionDTO(UserDTO dto, int rank) {
        this.dto = dto;
        this.rank = rank;
    }

   

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public UserDTO getDto() {
        return dto;
    }

    public void setDto(UserDTO dto) {
        this.dto = dto;
    }


}
