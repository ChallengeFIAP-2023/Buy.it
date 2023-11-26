// Component import
import {
    DecreasingContainer,
    StatusBar,
    Header,
    Highlight,
    Input,
    Button,
  } from "@components/index";
  
  // Style import
  import { Container, Fieldset, Requirement, RequirementText } from './styles';
  import { ArrowRight, Check, X } from "phosphor-react-native";
import theme from "@theme/index";
import { useState } from "react";
import { NativeSyntheticEvent, TextInputChangeEventData } from "react-native";
  
  export function Step5({ navigation }: any) {

    const [password, setPassword] = useState('')
    const [repeatedPassword, setRepeatedPassword] = useState('')
  
    return (
      <Container>
        {/* Arrumar esse dois componentes abaixo */}
        {/* <StatusBar /> */}
        <Header />
  
        <Highlight
          title="Senha"
          subtitle="Passo 5 de 5"
        />
  
        <DecreasingContainer>
          <Fieldset>
            <Input
              label="Senha"
              placeholder="********"
              secureTextEntry
              onChange={(e: NativeSyntheticEvent<TextInputChangeEventData>) => setPassword(e.nativeEvent.text) }
              value={password}
            />
          </Fieldset>

          <Fieldset>
            <Input
              label="Repita sua senha"
              placeholder="********"
              secureTextEntry
              onChange={(e: NativeSyntheticEvent<TextInputChangeEventData>) => setRepeatedPassword(e.nativeEvent.text) }
              value={repeatedPassword}
            />
          </Fieldset>

          <Requirement>
            <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
            <RequirementText fulfilled>8 caracteres</RequirementText>
          </Requirement>

          <Requirement>
            <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
            <RequirementText fulfilled>pelo menos uma letra minúscula</RequirementText>
          </Requirement>

          <Requirement>
            <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
            <RequirementText>pelo menos uma letra minúscula</RequirementText>
          </Requirement>
  
        </DecreasingContainer>
  
        <Button 
          label="Criar conta"
          size="XL"
          icon={<ArrowRight color={'#fff'} weight="bold" />}
          bottom
          onPress={() => navigation.navigate("Step3")}
        />
      </Container>
    );
  }
  