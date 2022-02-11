package com.golfie.unit.common;

import com.golfie.common.database.ReplicationDataSourceRouter.DataSourceNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataSourceRoutingTest {

    @DisplayName("slave db를 번갈아 가면서 조회한다.")
    @Test
    void routing_Test_Equal() {
        DataSourceNames<String> dataSourceNames = new DataSourceNames<>(List.of("slave1", "slave2"));

        assertThat(dataSourceNames.getNext()).isEqualTo("slave1");
        assertThat(dataSourceNames.getNext()).isEqualTo("slave2");
        assertThat(dataSourceNames.getNext()).isEqualTo("slave1");
        assertThat(dataSourceNames.getNext()).isEqualTo("slave2");
    }

    @DisplayName("slave db를 번갈아 가면서 조회한다.")
    @Test
    void routing_Test_Not_Equal() {
        DataSourceNames<String> dataSourceNames = new DataSourceNames<>(List.of("slave1", "slave2"));

        assertThat(dataSourceNames.getNext()).isNotEqualTo("slave2");
        assertThat(dataSourceNames.getNext()).isNotEqualTo("slave1");
        assertThat(dataSourceNames.getNext()).isNotEqualTo("slave2");
        assertThat(dataSourceNames.getNext()).isNotEqualTo("slave1");
    }
}
