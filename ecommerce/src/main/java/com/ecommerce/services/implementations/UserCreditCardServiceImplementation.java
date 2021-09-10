package com.ecommerce.services.implementations;

import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import com.ecommerce.services.specifications.UserCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserCreditCardServiceImplementation implements UserCreditCardService {

    private final UserCreditCardRepository crediCardRepository;

    @Autowired
    public UserCreditCardServiceImplementation(UserCreditCardRepository crediCardRepository){
        this.crediCardRepository = crediCardRepository;
    }

    @Override
    public UserCreditCard findById(Long id) {
        return crediCardRepository.findById(id);
    }

    @Override
    public void save(UserCreditCard userCreditCard, Long id) {
        crediCardRepository.save(userCreditCard, id);
    }

    @Override
    public boolean existsById(Long id) {
        return crediCardRepository.existsById(id);
    }

    @Override
    public void delete(Long idUser) {
        crediCardRepository.delete(idUser);
    }

    @Override
    public void deleteSpecificCard(Long cardId) {
        crediCardRepository.deleteSpecificCard(cardId);
    }

    @Override
    public List<UserCreditCard> findUserCreditCards(Long id) {
        return crediCardRepository.findUserCreditCards(id);
    }
}
