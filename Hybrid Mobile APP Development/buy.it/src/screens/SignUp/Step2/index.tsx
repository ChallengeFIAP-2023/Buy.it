// Component import
import {
  DecreasingContainer,
  StatusBar,
  Header,
  Highlight,
  Input,
  Button,
  Chip
} from "@components/index";

// Style import
import { Container, Fieldset, WrapChip } from './styles';
import { ArrowRight } from "phosphor-react-native";

export function Step2({ navigation }) {

  return (
    <Container>
      {/* Arrumar esse dois componentes abaixo */}
      {/* <StatusBar /> */}
      {/* <Header /> */}

      <Highlight
        title="Dados da empresa"
        subtitle="Passo 2 de 6"
      />

      <DecreasingContainer>
        <Fieldset>
          <Input
            label="Nome da empresa"
            placeholder="Buy.it"
          />
        </Fieldset>

        {/* TODO: adicionar um mask nesse input */}
        <Fieldset>
          <Input
            label="CNPJ"
            placeholder="johndoe@example.com"
          />
        </Fieldset>

        {/* TODO: isso vai ser um select com pesquisa. Caso não exista na pesquisa o que ele procura, será criado um novo departamento */}
        <Fieldset>
          <Input
            label="Departamento"
            placeholder="Escritório"
          />
        </Fieldset>

        {/* TODO: isso vai ser um select com pesquisa. Caso não exista na pesquisa o que ele procura, será criada uma nova tag */}
          <Input
            label="Tags relacionadas"
            placeholder="Papelaria"
          />

        <WrapChip>
          <Chip value="material escolar" removable/>
          <Chip value="suprimento" removable/>
          <Chip value="papelaria" removable/>
        </WrapChip>

      </DecreasingContainer>

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
