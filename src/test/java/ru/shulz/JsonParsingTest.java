package ru.shulz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.shulz.model.JsonData;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParsingTest {
    @Test
    void jsonParsFromJecksonTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File json = new File("src/test/resources/json/test.json");
        JsonData jsonData = mapper.readValue(json, JsonData.class);

        assertThat(jsonData.id).isEqualTo("0eec70e0-7167-11ed-b705-fbb9924f0f8a");
        assertThat(jsonData.title).isEqualTo("Tестировщик");
        assertThat(jsonData.name).isEqualTo("Просто Лось");
        assertThat(jsonData.phone).isEqualTo("+79118910523");
        assertThat(jsonData.email).isEqualTo("123@mail.com");
        assertThat(jsonData.education).isEqualTo("Высшее");
        assertThat(jsonData.work).isTrue();
        assertThat(jsonData.keySkills.contains("Стремление к профессиональному и карьерному росту"));
        assertThat(jsonData.keySkills.contains("Быстрая обучаемость"));
        assertThat(jsonData.keySkills.get(2)).isEqualTo("Ответственность");
        assertThat(jsonData.keySkills.get(3)).isEqualTo("Аккуратность");
        assertThat(jsonData.address.country).isEqualTo("Россия");
        assertThat(jsonData.address.city).isEqualTo("Екатеринбург");
    }
}
