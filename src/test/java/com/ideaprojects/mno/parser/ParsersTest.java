package com.ideaprojects.mno.parser;

import com.ideaprojects.mno.model.CallPrices;
import com.ideaprojects.mno.model.Parameter;
import com.ideaprojects.mno.model.Tariff;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsersTest {

    private final File xml = new File("src/main/resources/tariffs.xml");

    @Test
    void domParserParsesAllFields() throws Exception {
        List<Tariff> list = DomParser.parse(xml);
        assertCommonAssertions(list);
    }

    @Test
    void saxParserParsesAllFields() throws Exception {
        List<Tariff> list = SaxParser.parse(xml);
        assertCommonAssertions(list);
    }

    @Test
    void staxParserParsesAllFields() throws Exception {
        List<Tariff> list = StaxParser.parse(xml);
        assertCommonAssertions(list);
    }

    @Test
    void allParsersProduceSameCountAndFirstIds() throws Exception {
        List<Tariff> d = DomParser.parse(xml);
        List<Tariff> s = SaxParser.parse(xml);
        List<Tariff> x = StaxParser.parse(xml);
        assertEquals(d.size(), s.size());
        assertEquals(d.size(), x.size());
        // compare IDs order
        assertEquals(mapIds(d), mapIds(s));
        assertEquals(mapIds(d), mapIds(x));
    }

    private List<String> mapIds(List<Tariff> list){
        return list.stream().map(Tariff::getId).toList();
    }

    private void assertCommonAssertions(List<Tariff> list){
        assertEquals(3, list.size());
        Tariff t1 = list.get(0);
        assertEquals("t1", t1.getId());
        assertEquals("Light", t1.getName());
        assertEquals("MobiUA", t1.getOperatorName());
        assertEquals(new BigDecimal("0.00"), t1.getPayroll());
        assertEquals(new BigDecimal("0.50"), t1.getSmsPrice());
        CallPrices cp = t1.getCallPrices();
        assertEquals(new BigDecimal("0.10"), cp.getInsideNetwork());
        assertEquals(new BigDecimal("0.50"), cp.getOutsideNetwork());
        assertEquals(new BigDecimal("0.30"), cp.getLandline());
        List<Parameter> params = t1.getParameters();
        assertEquals(1, params.size());
        Parameter p = params.get(0);
        assertEquals("default", p.getNameAttr());
        assertEquals(1, p.getFavoriteNumberCount());
        assertEquals("12-seconds", p.getTariffization());
        assertEquals(new BigDecimal("10.00"), p.getActivationFee());
    }
}
