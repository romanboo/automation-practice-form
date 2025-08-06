package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.time.LocalDateTime;

import models.DataModel;
import static org.junit.jupiter.api.Assertions.*;

public class JsonFileTest {
    @Test
    void testJsonParsing() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream is = getClass().getClassLoader().getResourceAsStream("sample_json.json");
        assertNotNull(is, "Файл не найден");

        mapper.registerModule(new JavaTimeModule());

        DataModel data = mapper.readValue(is, DataModel.class);

        assertEquals(1752745727137755100L, data.getVersion());
        assertEquals("05bdfcc1-863b-43e0-82f6-a73433f3189b", data.getMdmId());
        assertEquals(43, data.getSourcechannel());
        assertEquals(false, data.isDeleted());
        LocalDateTime expected = LocalDateTime.of(2025, 7, 10, 17, 39, 44);
        assertEquals(expected, data.getLastUpdateDt());

    }
}