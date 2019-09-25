<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<xsl:text>&#13;</xsl:text>
    <entries>
        <xsl:for-each select="entries/entry">
		<xsl:text>&#10;</xsl:text>
		<xsl:text>&#9;</xsl:text>
            <entry>
                <xsl:attribute name="href">
					<xsl:value-of select="field"/>
                </xsl:attribute>
            </entry>
			
        </xsl:for-each>
		<xsl:text>&#10;</xsl:text>
    </entries>
</xsl:template>
</xsl:stylesheet>