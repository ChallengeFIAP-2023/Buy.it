import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { useLayoutEffect, useState } from 'react';
import { SubmitHandler } from 'react-hook-form';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';
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

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';

//Hooks import
import { useTags } from '@hooks/useTags';
import { useDepartments } from '@hooks/useDepartments';
import { useQuoteDetails } from '@hooks/useQuoteDetails';

interface Step1Form {
  idDepartamento: number;
  idsTags: number;
}

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {

  // State
  const [selectedTags, setSelectedTags] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState<number>();

  const { tags, handleGetTags, tagsLoading } = useTags();
  const { departments, handleGetDepartments, departmentsLoading } = useDepartments();
  const { product, setProduct } = useQuoteDetails();

  const onSubmit = () => {
    const idDepartamento = selectedDepartment as number;
    const idsTags = selectedTags as number[];

    setProduct(prevProd => ({ ...prevProd, idDepartamento, idsTags }));
    
    return navigation.navigate('Step2');
  };

  useLayoutEffect(() => {
    handleGetTags();
    handleGetDepartments();
  }, []);

  const hasDepartments = !departmentsLoading && departments.length >= 1;
  const departmentsOptions: TFlatList = departments.map(dep => ({
    label: dep.nome,
    value: dep.id,
  }));

  const hasTag = !tagsLoading && tags.length >= 1;
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
          
          {!departmentsLoading && departments && (
            <Fieldset>
              <CustomDropdown
                label="Departamento"
                isSearchable={hasDepartments}
                placeholder="Selecione o departamento relacionado"
                options={departmentsOptions}
                selectedValue={selectedDepartment}
                onValueChange={(value: number) => setSelectedDepartment(value)}
                listControls={{ emptyListMessage: 'Nenhum departamento encontrado' }}
              />
            </Fieldset>
          )}

          {!tagsLoading && tags && (
            <>
              <Fieldset>
                <CustomDropdown
                  label="Tags relacionadas"
                  isSearchable={hasTag}
                  isMultiple
                  placeholder="Selecione as tags relacionadas"
                  options={tagsOptions}
                  selectedValue={selectedTags}
                  onValueChange={(value: []) => setSelectedTags(value)}
                  listControls={{ emptyListMessage: 'Nenhuma tag encontrada' }} />
              </Fieldset>
              
              <Button
                label="Nova tag"
                size="SM" />
            </>
          )}
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
