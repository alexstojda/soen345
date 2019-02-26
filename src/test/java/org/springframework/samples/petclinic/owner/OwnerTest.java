package org.springframework.samples.petclinic.owner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class OwnerTest {

    private Owner owner;
    @Mock
    private Pet newPet;
    @Mock
    private Pet oldPet;

    @Before
    public void setUp(){
        owner = new Owner();
        when(newPet.isNew()).thenReturn(true);
        when(newPet.getName()).thenReturn("new");
        when(oldPet.isNew()).thenReturn(false);
        when(oldPet.getName()).thenReturn("old");
        Set<Pet> petSet = new HashSet<>();
        petSet.add(oldPet);
        petSet.add(newPet);
        owner.setPetsInternal(petSet);
    }

    @Test
    public void getPetTest() {

        for (Pet pet : owner.getPetsInternal()) {
            System.out.println(pet.getName());
            System.out.println(pet.isNew());
        }

        Assert.assertEquals(oldPet, owner.getPet("old" , false));
        Assert.assertEquals(oldPet, owner.getPet("old" , true));
        Assert.assertEquals(newPet, owner.getPet("new" , false));
        Assert.assertEquals(null, owner.getPet("new" , true));
    }


}
