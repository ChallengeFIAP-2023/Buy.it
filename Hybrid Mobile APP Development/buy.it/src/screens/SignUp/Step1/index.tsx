// Component import
import {
    DecreasingContainer,
    StatusBar,
    Header,
    Highlight,
    Input,
    Button
  } from "@components/index";
  
  // Style import
  import { Container, Fieldset, RegisterText, Touchable, RegisterTextBold } from './styles';
  
  export function Step1() {
    return (
      <Container>
        {/* Arrumar esse dois componentes abaixo */}
        {/* <StatusBar /> */}
        {/* <Header /> */}
  
        <Highlight
          title="Qual a intenção da sua empresa?"
          subtitle="Passo 1 de 6"
        />
  
        <DecreasingContainer>
          <Fieldset>
            <Input
              label="E-mail ou CNPJ"
              placeholder="Johndoe@example.com"
            />
          </Fieldset>
  
          <Fieldset>
            <Input
              label="Senha"
              placeholder="Johndoe@example.com"
              secureTextEntry
            />
          </Fieldset>
  
          <Button label="Entrar" />
  
          <Touchable>
            <RegisterText>Novo por aqui?</RegisterText>
            <RegisterTextBold>Criar uma conta</RegisterTextBold>
          </Touchable>
        </DecreasingContainer>
      </Container>
    );
  }
  