package io.randomizer.name_blender;

import io.randomizer.name_blender.service.NameBlenderService;
import io.randomizer.name_blender.service.NameBlenderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class NameBlenderServiceUnitTest {

    NameBlenderService nameBlenderService = new NameBlenderServiceImpl();

    @Test
    void blendName() throws Exception {
        nameBlenderService.blendName();
    }
}