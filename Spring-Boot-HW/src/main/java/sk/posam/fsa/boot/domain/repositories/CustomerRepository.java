package sk.posam.fsa.boot.domain.repositories;

import sk.posam.fsa.boot.domain.Customer;

public interface CustomerRepository {
    Customer get(long id);
}
