package pt.isec.pa.javalife.model.command.commands;

import org.junit.jupiter.api.Test;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;
import pt.isec.pa.javalife.model.data.elements.Fauna;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RemoveElementoCmdTest {

    @Test
    public void execute_RemoveElementoCmd_ElementoRemovidoDoEcossistema() throws CloneNotSupportedException {
        EcosystemManager manager = new EcosystemManager();
        manager.createEcosystem(400,500,400);
        Area area = new Area(10,20,10,20);
        Fauna element = new Fauna(area,new Ecosystem(400,400),null);
        manager.addElemento(element);
        int id =1 ;
        Element type = Element.FAUNA ;
        RemoveElementoCmd comando = new RemoveElementoCmd(manager, id, type);
        comando.execute();
        assertFalse(manager.getElementos().contains(element));
    }
}