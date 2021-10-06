package gp.service;

import gp.domain.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final CartRepository cartRepository;


}
