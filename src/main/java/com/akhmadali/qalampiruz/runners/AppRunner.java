package com.akhmadali.qalampiruz.runners;

import com.akhmadali.qalampiruz.dto.CategoryDto;
import com.akhmadali.qalampiruz.entity.Category;
import com.akhmadali.qalampiruz.entity.Role;
import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.enums.ERole;
import com.akhmadali.qalampiruz.enums.Status;
import com.akhmadali.qalampiruz.repository.CategoryRepository;
import com.akhmadali.qalampiruz.repository.RoleRepository;
import com.akhmadali.qalampiruz.repository.UserRepository;
import com.akhmadali.qalampiruz.service.impl.CategoryService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.util.resources.cldr.ti.CalendarData_ti_ET;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Role settings
        Role roleUser = new Role();
        roleUser.setName(ERole.ROLE_USER);
        roleUser.setCreated(new Date());
        roleUser.setStatus(Status.ACTIVE);
        Role roleAdmin = new Role();
        roleAdmin.setName(ERole.ROLE_ADMIN);
        roleAdmin.setCreated(new Date());
        roleAdmin.setStatus(Status.ACTIVE);
        Role roleModerator = new Role();
        roleModerator.setName(ERole.ROLE_MODERATOR);
        roleModerator.setCreated(new Date());
        roleModerator.setStatus(Status.ACTIVE);

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleModerator);

        //---------------------------//

        //Admin settings
        User superUser = new User();
        superUser.setUsername("admin");
        superUser.setPassword(passwordEncoder.encode("ahmadali1976"));
        superUser.setFirstName("Admin");
        superUser.setLastName("Adminov");
        superUser.setStatus(Status.ACTIVE);
        Set<Role> superUserRoles = new HashSet<>();
        superUserRoles.add(roleUser);
        superUserRoles.add(roleModerator);
        superUserRoles.add(roleAdmin);
        superUser.setRoles(superUserRoles);
        userRepository.save(superUser);

        //---------------------------//

        //Category settings
        Category categorySociety = new Category();
        categorySociety.setNameEn("Local");
        categorySociety.setNameLatin("Jamiyat");
        categorySociety.setNameKiril("Жамият");
        categorySociety.setCreated(new Date());
        categoryRepository.save(categorySociety);

        Category categoryWorld = new Category();
        categoryWorld.setNameEn("World");
        categoryWorld.setNameLatin("Olam");
        categoryWorld.setNameKiril("Олам");
        categoryWorld.setCreated(new Date());
        categoryRepository.save(categoryWorld);

        Category categoryArtCulture = new Category();
        categoryArtCulture.setNameEn("Art & Culture");
        categoryArtCulture.setNameLatin("Madaniyat va san'at");
        categoryArtCulture.setNameKiril("Маданият ва санъат");
        categoryArtCulture.setCreated(new Date());
        categoryRepository.save(categoryArtCulture);

        Category categoryInterview = new Category();
        categoryInterview.setNameEn("Interviews");
        categoryInterview.setNameLatin("Intervyu");
        categoryInterview.setNameKiril("Интервю");
        categoryInterview.setCreated(new Date());
        categoryRepository.save(categoryInterview);

        Category categoryHelpful = new Category();
        categoryHelpful.setNameEn("Helpful");
        categoryHelpful.setNameLatin("Foydali");
        categoryHelpful.setNameKiril("Фойдали");
        categoryHelpful.setCreated(new Date());
        categoryRepository.save(categoryHelpful);

        Category categorySport = new Category();
        categorySport.setNameEn("Sport");
        categorySport.setNameLatin("Sport");
        categorySport.setNameKiril("Спорт");
        categorySport.setCreated(new Date());
        categoryRepository.save(categorySport);

        Category categoryReview = new Category();
        categoryReview.setNameEn("Review");
        categoryReview.setNameLatin("Tahlil");
        categoryReview.setNameKiril("Таҳлил");
        categoryReview.setCreated(new Date());
        categoryRepository.save(categoryReview);

        CategoryDto categoryCrime = new CategoryDto();
        categoryCrime.setNameEn("Crime");
        categoryCrime.setNameLatin("3-sahifa");
        categoryCrime.setNameKiril("3-саҳифа");
        categoryCrime.setCategoryId(1);
        categoryService.add(categoryCrime);

        CategoryDto categoryTechNews = new CategoryDto();
        categoryTechNews.setNameEn("Tech News");
        categoryTechNews.setNameLatin("Texnologiya");
        categoryTechNews.setNameKiril("Технология");
        categoryTechNews.setCategoryId(5);
        categoryService.add(categoryTechNews);

        CategoryDto categoryWellness = new CategoryDto();
        categoryWellness.setNameEn("Wellness");
        categoryWellness.setNameLatin("Salomatlik");
        categoryWellness.setNameKiril("Саломатлик");
        categoryWellness.setCategoryId(5);
        categoryService.add(categoryWellness);

        CategoryDto categoryInteresting = new CategoryDto();
        categoryInteresting.setNameEn("Interesting");
        categoryInteresting.setNameLatin("Bu qiziq");
        categoryInteresting.setNameKiril("Бу қизиқ");
        categoryInteresting.setCategoryId(5);
        categoryService.add(categoryInteresting);

        CategoryDto categoryBusiness = new CategoryDto();
        categoryBusiness.setNameEn("Business");
        categoryBusiness.setNameLatin("Biznes");
        categoryBusiness.setNameKiril("Бизнес");
        categoryBusiness.setCategoryId(5);
        categoryService.add(categoryBusiness);


    }
}
