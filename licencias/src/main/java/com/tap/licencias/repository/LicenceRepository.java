package com.tap.licencias.repository;

import com.tap.licencias.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Integer> {

    @Query(value = "SELECT l from Licence l WHERE l.user.id = ?1")
    public ArrayList<Licence> getByUserID(Integer idUser);

    @Query(value = "SELECT l from Licence l WHERE l.lastOne = true")
    public ArrayList<Licence> getByUserIDLastOne(Integer idUser);

    @Query(value = "SELECT l from Licence l WHERE l.state.id <> ?1 and l.state.id <> ?2 ")
    public ArrayList<Licence> getLicencesInProcess(Integer state1, Integer state2);

    @Query(value = "SELECT l from Licence l WHERE l.lastOne = true and l.user.id = ?1")
    public Licence getLastLicence(Integer idUser);

    @Query(value = "SELECT l FROM Licence l WHERE l.user.id = ?1 and (l.state.id = ?2 or l.state.id = ?3 or l.state.id = ?4 or l.state.id = ?5)")
    public Licence getLicenceInProcess(Integer idUser, Integer InitialState, Integer AwaitingPrintingState, Integer ReadyState, Integer ToBeWithdrawn);


}
