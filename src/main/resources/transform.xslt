<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="http://com.ideaprojects.mno"
                exclude-result-prefixes="t">
    <xsl:output indent="yes"/>

    <xsl:key name="kByOperator" match="t:tariff" use="t:operatorName"/>

    <xsl:template match="/t:Tariff">
        <GroupedTariffs>
            <xsl:for-each select="t:tariff[generate-id() = generate-id(key('kByOperator', t:operatorName)[1])]">
                <Operator name="{t:operatorName}">
                    <xsl:for-each select="key('kByOperator', t:operatorName)">
                        <tariff>
                            <id><xsl:value-of select="@id"/></id>
                            <name><xsl:value-of select="t:name"/></name>
                            <payroll><xsl:value-of select="t:payroll"/></payroll>
                        </tariff>
                    </xsl:for-each>
                </Operator>
            </xsl:for-each>
        </GroupedTariffs>
    </xsl:template>
</xsl:stylesheet>
