// Component import
import {
    Highlight,
    Button,
    StatusBar,
    Header
} from "@components/index";

// Style import
import { Container, Content, Option, OptionText } from './styles';
import { useState } from "react";

import { ArrowRight } from "phosphor-react-native";
  
  export function Step1({ navigation }: any) {

    const [isSupplier, setSupplier] = useState(false)

    return (
      <Container>
        {/* Arrumar esse dois componentes abaixo */}
        {/* <StatusBar /> */}
        <Header />
  
        <Highlight
          title="Qual a intenção da sua empresa?"
          subtitle="Passo 1 de 5"
        />

        <Content>
            <Option onPress={() => setSupplier(false)} active={!isSupplier}>
                <OptionText active={!isSupplier}> Comprar </OptionText>
            </Option>

            <Option onPress={() => setSupplier(true)} active={isSupplier}>
                <OptionText active={isSupplier}> Fornecer </OptionText>
            </Option>
        </Content>

        <Button 
            label="Continuar"
            size="XL"
            icon={<ArrowRight color={'#fff'} weight="bold" />}
            bottom
            onPress={() => navigation.navigate("Step2")}
        />
      </Container>
    );
  }
  