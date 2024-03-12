import { Text } from 'react-native';
import { ArrowRight, IconProps } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Component import
import {
  Button,
  CustomDropdown,
  DecreasingContainer,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'De que tipo de produto você precisa hoje?',
            subtitle: 'Passo 1 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <Fieldset>
            <CustomDropdown
              label="Departamento"
              // isSearchable={hasTag}
              isMultiple
              placeholder="Escritório"
              options={[]}
              selectedValue={null}
              onValueChange={(value: []) => {}}
              listControls={{
                emptyListMessage: 'Nenhum departamento encontrado.',
              }}
            />
          </Fieldset>

          <Fieldset>
            <CustomDropdown
              label="Tags relacionadas"
              // isSearchable={hasTag}
              isMultiple
              placeholder="Papelaria"
              options={[]}
              selectedValue={null}
              onValueChange={(value: []) => {}}
              listControls={{ emptyListMessage: 'Nenhuma tag encontrada' }}
            />
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate('Step2')}
      />
    </WrapperPage>
  );
};
