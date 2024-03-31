import { useLayoutEffect, useState } from 'react';
import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

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

//Hooks import
import { useCreateQuote } from '@hooks/useCreateQuote';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [selectedTags, setSelectedTags] = useState<number[]>([]);
  const [selectedDepartment, setSelectedDepartment] = useState<number>(0);

  const { 
    setProduct, 
    tags, 
    departments, 
    loading,
    fetchDepartmentsAndTags 
  } = useCreateQuote();

  useLayoutEffect(() => {
    fetchDepartmentsAndTags();
  }, []);

  const onSubmit = () => {
    const idDepartamento = selectedDepartment;
    const idsTags = selectedTags;

    const findError = () => {
      if (!idDepartamento || idDepartamento == 0) return 'Selecione o departamento!' 
      if (!idsTags || idsTags.length <= 0) return 'Selecione pelo menos uma tag!'
      return null;
    };

    const error = findError();

    if (error) 
      return Toast.show({
        type: 'error',
        text1: error,
      });

    setProduct(prevProd => ({ ...prevProd, idDepartamento, idsTags }));

    return navigation.navigate('Step2');
  };

  const hasDepartments = !loading && departments.length >= 1;
  const departmentsOptions: TFlatList = departments.map(item => ({
    label: item.nome,
    value: item.id,
  }));

  const hasTag = !loading && tags.length >= 1;
  const tagsOptions: TFlatList = tags.map(tag => ({
    label: tag.nome,
    value: tag.id,
  }));

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
              isSearchable={hasDepartments}
              placeholder="Selecione o departamento relacionado"
              options={departmentsOptions}
              selectedValue={selectedDepartment}
              onValueChange={(value: number) => setSelectedDepartment(value)}
              listControls={{
                emptyListMessage: 'Nenhum departamento encontrado',
              }}
            />
          </Fieldset>

          <Fieldset>
            <CustomDropdown
              label="Tags relacionadas"
              isSearchable={hasTag}
              isMultiple
              placeholder="Selecione as tags relacionadas"
              options={tagsOptions}
              selectedValue={selectedTags}
              onValueChange={(value: []) => setSelectedTags(value)}
              listControls={{ emptyListMessage: 'Nenhuma tag encontrada' }}
            />
          </Fieldset>

          <Button label="Nova tag" size="SM" />
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => onSubmit()}
      />
    </WrapperPage>
  );
};
